package site.blmdz.enums;

/**
 * 用户状态
 * @author yangyz
 * @date 2016年12月2日下午5:25:03
 */
public enum UserStatus {
    /**
     * 已删除
     */
    DELETED(-3),
    
    /**
     * 已冻结
     */
    FROZEN(-2),
    
    /**
     * 已锁定
     */
    LOCKED(-1),
    
    /**
     * 未激活
     */
    NOT_ACTIVATE(0),
    
    /**
     * 正常
     */
    NORMAL(1);
	
	private final int value;
	
    public final int value() {
        return value;
    }
	
	UserStatus(int value) {
		this.value = value;
	}
}
