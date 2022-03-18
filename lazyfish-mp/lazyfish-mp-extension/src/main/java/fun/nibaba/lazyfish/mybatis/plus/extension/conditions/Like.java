package fun.nibaba.lazyfish.mybatis.plus.extension.conditions;

import fun.nibaba.lazyfish.mybatis.plus.extension.enums.LikeKeyword;
import lombok.Data;

import java.io.Serializable;

/**
 * 模糊
 *
 * @author chenjiamin
 * @date 2022-03-18 14:43:58
 */
@Data
public class Like implements Serializable {

    /**
     * 值
     */
    private String value;

    /**
     * 是否like
     */
    private boolean like = true;

    /**
     * 模糊方式
     */
    private LikeKeyword keyword = LikeKeyword.DEFAULT;

    public Like() {
        // 正常不调用这个，这个是给在远程传输后，进行反序列化使用
    }

    public Like(String value) {
        this(value, true);
    }

    public Like(String value, boolean like) {
        this(value, like, LikeKeyword.DEFAULT);
    }

    public Like(String value, LikeKeyword keyword) {
        this(value, true, keyword);
    }


    public Like(String value, boolean like, LikeKeyword keyword) {
        this.value = value;
        this.like = like;
        this.keyword = keyword;
    }
}
