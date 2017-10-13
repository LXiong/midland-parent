package com.midland.web.dao;

import com.midland.web.model.QuotationSecondHand;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuotationSecondHandMapper {

	QuotationSecondHand selectQuotationSecondHandById(Integer quotationSecondHand);

	int deleteQuotationSecondHandById(Integer quotationSecondHand);

	int updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand);

	int insertQuotationSecondHand(QuotationSecondHand quotationSecondHand);
	int insertQuotationSecondHandBatch(List list);

	List<QuotationSecondHand> findQuotationSecondHandList(QuotationSecondHand quotationSecondHand);

}
