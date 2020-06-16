package tech.chending.service.impl;

import tech.chending.entity.WachatUser;
import tech.chending.mapper.WachatUserDao;
import tech.chending.service.WachatUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WachatUser)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 18:21:52
 */
@Service("wachatUserService")
public class WachatUserServiceImpl implements WachatUserService {
    @Resource
    private WachatUserDao wachatUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WachatUser queryById(Integer id) {
        return this.wachatUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<WachatUser> queryAllByLimit(int offset, int limit) {
        return this.wachatUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param wachatUser 实例对象
     * @return 实例对象
     */
    @Override
    public WachatUser insert(WachatUser wachatUser) {
        this.wachatUserDao.insert(wachatUser);
        return wachatUser;
    }

    /**
     * 修改数据
     *
     * @param wachatUser 实例对象
     * @return 实例对象
     */
    @Override
    public WachatUser update(WachatUser wachatUser) {
        this.wachatUserDao.update(wachatUser);
        return this.queryById(wachatUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.wachatUserDao.deleteById(id) > 0;
    }

    /**
     * 通过openid判断是否存在该条数据
     * @param openid 主键
     * @return true 存在  false 不存在
     */
    @Override
    public boolean queryByOpenid(String openid) {
        return wachatUserDao.queryByOpenid(openid) != null;
    }
}