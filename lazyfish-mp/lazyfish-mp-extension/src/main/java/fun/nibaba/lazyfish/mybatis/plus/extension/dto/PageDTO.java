package fun.nibaba.lazyfish.mybatis.plus.extension.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;

/**
 * 分页传输实体
 * 可以带有参数传递
 * 例如在feign的内部远程调用的时候如果使用实体传输，默认为post请求，并且实体参数会在body内进行传输，一个请求只能有一个body导致远程分页查询的繁琐
 *
 * @author chenjiamin
 * @date 2022-03-18 15:08:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDTO<T> extends Page<T> {

    @Valid
    private T params;

    public PageDTO() {

    }

    public PageDTO(T params) {
        this.params = params;
    }

    public PageDTO(Page<?> page) {
        this(page, null);
    }

    public PageDTO(Page<?> page, T params) {
        this.setTotal(page.getTotal());
        this.setSize(page.getSize());
        this.setCurrent(page.getCurrent());
        this.setOrders(page.orders());
        this.setOptimizeCountSql(page.optimizeCountSql());
        this.setSearchCount(page.searchCount());
        this.params = params;
    }

}
