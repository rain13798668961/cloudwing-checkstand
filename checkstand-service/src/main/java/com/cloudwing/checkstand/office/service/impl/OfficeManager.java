package com.cloudwing.checkstand.office.service.impl;

import com.cloudwing.checkstand.office.entity.Office;
import com.cloudwing.checkstand.office.mapper.OfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfficeManager {

    @Autowired
    private OfficeMapper officeMapper;

    @Cacheable
    public List<Office> listAllOffice() {
        return officeMapper.selectAll();
    }

    public Map<Integer, Office> getIdMapOffice() {
        Map<Integer, Office> idMapOffice = new HashMap<Integer, Office>();
        List<Office> list = listAllOffice();
        for (Office off : list) {
            idMapOffice.put(off.getId(), off);
        }
        return idMapOffice;
    }

    /**
     * 根据办公现场id获取办公现场名称name
     * 获取不到返回null
     * @param id
     * @return
     */
    public String getOfficeNameById(Integer id) {
        Office off = getIdMapOffice().get(id);
        if (null != off) {
            return off.getName();
        }
        return null;
    }

    public Map<String, Office> getCodeMapOffice() {
        Map<String, Office> codeMapOffice = new HashMap<String, Office>();
        List<Office> list = listAllOffice();
        for (Office off : list) {
            codeMapOffice.put(off.getCode(), off);
        }
        return codeMapOffice;
    }

    /**
     * 根据办公现场代码code获取办公现场主键id
     * 获取不到时返回null
     * @param code
     * @return
     */
    public Integer getIdByCode(String code) {
        Office off = getCodeMapOffice().get(code);
        if (null != off) {
            return off.getId();
        }
        return null;
    }

    public List<Integer> getIdByUserId(Integer userId) {
        List<Office> l = officeMapper.listByUserId(userId);
        List<Integer> li = new ArrayList<Integer>();
        for (Office office : l) {
            li.add(office.getId());
        }
        return li;
    }

}
