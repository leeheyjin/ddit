package com.suin.zzang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.suin.zzang.vo.SusuVO;

@Mapper    // 명시적 표현 DAO에 해당
public interface SusuMapper {
    // 읽기 리스트(여러 줄)
    public List<SusuVO> listData();

    // 읽기 한줄
    public SusuVO oneData(SusuVO susuVO);

    // 입력
    public int insertData(SusuVO susuVO);

    // 수정
    public int updateData(SusuVO susuVO);

    // 삭제
    public int deleteData(SusuVO susuVO);
}
