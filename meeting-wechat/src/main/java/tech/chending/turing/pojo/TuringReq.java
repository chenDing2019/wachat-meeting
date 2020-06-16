
package tech.chending.turing.pojo;

import lombok.Data;


/**
 * @author ChenDing
 */
@Data
public class TuringReq {

    private int reqType;
    private Perception perception;
    private UserInfo userInfo;

}