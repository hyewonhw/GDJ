package com.gdu.mysql.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadDTO {
	private int uploadNo;
	private String title;
	private String content;
	private Date createDate;
	private Date modifyDate;
	private int attachCnt;
}

// java.sql.Date  -> 2022-12-13
// java.util.Date -> Tue Dec 13 19:39:48 KST 2022
