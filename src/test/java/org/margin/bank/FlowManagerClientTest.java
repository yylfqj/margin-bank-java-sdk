package org.margin.bank;

import org.junit.Before;
import org.junit.Test;
import org.margin.bank.client.FlowManagerClient;
import org.margin.bank.client.ManagerMarginClient;
import org.margin.bank.request.FlowDetail;
import org.margin.bank.request.MarginRequest;
import org.margin.bank.request.ResultData;
import org.margin.bank.response.MarginResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FlowManagerClientTest
 * @Author yyl
 * @Date 2022-03-16 19:59:06
 * @Description FlowManagerClientTest
 * @Version 1.0
 */
public class FlowManagerClientTest {

    private ManagerMarginClient managerClient;
    private FlowManagerClient flowManagerClient;

    @Before
    public void before() {
        this.managerClient = new ManagerMarginClient("20140101000000001", "RSA私钥")
                .hostname("http://localhost:8790").build();
        this.flowManagerClient = this.managerClient.flowManagerClient();
    }

    @Test
    public void pushFlow() throws IOException {
        MarginRequest<List<FlowDetail>> marginRequest = new MarginRequest();
        List<FlowDetail> flowDetails = new ArrayList<>();
        FlowDetail flowDetail = new FlowDetail();
        flowDetail.setSerialNumber("001");
        flowDetail.setBankCard("b-001");
        flowDetail.setBankName("b");
        flowDetail.setBelongNumber("2022-01");
        flowDetail.setFlowType("1");
        flowDetail.setFlowDirection("1");
        flowDetail.setOppositeBankCard("o-101");
        flowDetail.setOppositeBankName("o");
        flowDetail.setOppositeOrgCode("001");
        flowDetail.setOppositeOrgName("中国人民银行**分行");
        flowDetail.setOppositeIdNumber("3300**********");
        flowDetail.setAmount("100.10");
        flowDetail.setBalanceAfterChange("10220203.41");
        flowDetail.setOperationTime("2020-01-01 11:11:11.026");
        flowDetail.setRemarks("备注");
        flowDetails.add(flowDetail);
        marginRequest.setData(flowDetails);
        MarginResponse<List<ResultData>> marginResponse = flowManagerClient.batchBankFlow(marginRequest).syncExecute();

        if (marginResponse.isSuccess()) {
            //推送的数据更新标记为已推送
        } else {
            //打印错误
            List<ResultData> result = marginResponse.getResult();
            if (result == null || result.isEmpty()) {
                //所有数据都为推送失败
            } else {
                //遍历result,result中的数据为推送失败
            }
        }
    }

}
