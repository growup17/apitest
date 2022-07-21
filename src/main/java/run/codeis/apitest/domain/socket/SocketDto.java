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
	private Integer parallelNum = 1;
	private String request;
	private String response;
	private long spendTime;
}
