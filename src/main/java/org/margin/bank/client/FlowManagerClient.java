package org.margin.bank.client;

import com.google.gson.reflect.TypeToken;
import org.margin.bank.exceptions.BankException;
import org.margin.bank.exceptions.BankExceptionEnum;
import org.margin.bank.http.BankHttpCall;
import org.margin.bank.request.FlowDetail;
import org.margin.bank.request.MarginRequest;
import org.margin.bank.request.ResultData;
import org.margin.bank.response.MarginResponse;
import org.margin.bank.utils.RsaUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName FlowManagerClient
 * @Author yyl
 * @Date 2022-03-12 17:21:51
 * @Description 流水管理
 * @Version 1.0
 */
public class FlowManagerClient {

    private final ManagerMarginClient client;

    public FlowManagerClient(ManagerMarginClient client) {
        this.client = client;
    }

    /**
     * 流水批量推送
     * @param marginRequest
     * @return
     */
    public BankHttpCall<MarginResponse<List<ResultData>>> batchBankFlow(MarginRequest<List<FlowDetail>> marginRequest) {
        String url = this.client.hostname + "/bank/api/flow";
        this.CheckData(marginRequest);
        List<FlowDetail> data = marginRequest.getData();
        data.sort(Comparator.comparing(FlowDetail::getSerialNumber).thenComparing(FlowDetail::getOperationTime));
        //加签名
        String signParam = getSign(data);
        marginRequest.setSign(signParam);
        marginRequest.setAgencyCode(this.client.agencyCode);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        marginRequest.setTimestamp(localTime);
        return this.client.createHttpPostCall(url, this.client.json.toJson(marginRequest), new TypeToken<MarginResponse<List<ResultData>>>(){});
    }

    private void CheckData(MarginRequest<List<FlowDetail>> marginRequest){
        if (marginRequest == null || marginRequest.getData() == null || marginRequest.getData().isEmpty()) {
            throw new BankException(BankExceptionEnum.LOCAL_CHECK_DATA_ERROR);
        }
    }

    private String getSign(List<FlowDetail> flowDetails) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", flowDetails);
        String json = this.client.json.toJson(map);
        TreeMap<String, Object> treeMap = this.client.json.fromJson(json, TreeMap.class);
        return RsaUtils.generateSign(treeMap, this.client.privateKey);
    }

}
