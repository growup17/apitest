package run.codeis.apitest.domain.socket;

import lombok.Data;

/**
 * @author liubinqiang
 * @date 2022-7-20
 */
@Data
public class SocketDto implements Cloneable {
	/**
	 * 服务器ip
	 */
	private String serverIp;
	/**
	 * 服务器端口
	 */
	private Integer serverPort;
	/**
	 * 并行数
	 */
	private Integer parallelNum = 1;
	/**
	 * 请求
	 */
	private String request;
	/**
	 * 响应
	 */
	private String response;
	/**
	 * 链接信息
	 */
	private String connectionInfo;
	/**
	 * 处理该请求的线程名称
	 */
	private String threadName;
	/**
	 * 耗时
	 */
	private long spendTime;
	/**
	 * 读取超时时间
	 */
	private int readTimeout = 10000;

	@Override
	public SocketDto clone() {
		try {
			SocketDto clone = (SocketDto) super.clone();
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
