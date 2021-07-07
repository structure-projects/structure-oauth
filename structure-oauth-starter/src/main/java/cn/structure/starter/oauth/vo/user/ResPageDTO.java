package cn.structure.starter.oauth.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分页出参 - VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:31
 */
@Data
@ApiModel(description = "出参 ：分页 - DTO")
public class ResPageDTO<T> implements Serializable {

    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer current;
    @ApiModelProperty(value = "总页数", example = "20")
    private Integer pages;
    @ApiModelProperty(value = "记录数据")
    private List<T> records;
    @ApiModelProperty(value = "页大小")
    private Integer size;
    @ApiModelProperty(value = "总条数")
    private Long total;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResPageDTO{" +
                "current=" + current +
                ", pages=" + pages +
                ", records=" + records +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}

