package netmera4j.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import netmera4j.model.notification.SingleResult;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class GetPushResultResponse {
    private String nextPage;
    private List<SingleResult> list;
}
