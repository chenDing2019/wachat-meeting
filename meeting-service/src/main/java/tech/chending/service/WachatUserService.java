package tech.chending.service;

import tech.chending.entity.WachatUser;
import java.util.List;

/**
 * (WachatUser)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 18:21:52
 */
public interface WachatUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WachatUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WachatUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param wachatUser 实例对象
     * @return 实例对象
     */
    WachatUser insert(WachatUser wachatUser);

    /**
     * 修改数据
     *
     * @param wachatUser 实例对象
     * @return 实例对象
     */
    WachatUser update(WachatUser wachatUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过openid判断是否存在该条数据
     * @param openid 主键
     * @return openid
     */
    boolean queryByOpenid(String openid);

}