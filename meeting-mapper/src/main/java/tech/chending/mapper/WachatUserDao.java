package tech.chending.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.chending.entity.WachatUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (WachatUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 18:16:31
 */
public interface WachatUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WachatUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WachatUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param wachatUser 实例对象
     * @return 对象列表
     */
    List<WachatUser> queryAll(WachatUser wachatUser);

    /**
     * 新增数据
     *
     * @param wachatUser 实例对象
     * @return 影响行数
     */
    int insert(WachatUser wachatUser);

    /**
     * 修改数据
     *
     * @param wachatUser 实例对象
     * @return 影响行数
     */
    int update(WachatUser wachatUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过openid查询单条数据
     *
     * @param openid 主键
     * @return openid
     */
    String queryByOpenid(String openid);

}