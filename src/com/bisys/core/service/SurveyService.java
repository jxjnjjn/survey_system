package com.bisys.core.service;

import java.util.List;

import com.bisys.core.entity.survey.SurveyInfoEntity;

public interface SurveyService {
	
	public List<SurveyInfoEntity> getSurveyInfo(int status) throws Exception;
	
	
}
