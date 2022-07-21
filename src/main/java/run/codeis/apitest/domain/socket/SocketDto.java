package run.codeis.apitest.domain.socket;

import lombok.Data;

/**
 * @author liubinqiang
 * @date 2022-7-20
 */
@Data
public class SocketDto {
	private String ip;
	private Integer port;
	private String request;
	private String response;
}
