package com.maxcar.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.user.dao.MarketMapper;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.MarketExample;
import com.maxcar.user.service.MarketService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("marketService")
public class MarketServiceImpl extends BaseServiceImpl<Market, String> implements MarketService {


    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private CityService cityService;

    @Override
    public BaseDao<Market, String> getBaseMapper() {
        return marketMapper;
    }

    @Override
    public Market getMarket(String id) {
        Market market = new Market();
        try {
            market = marketMapper.getMarket(id);
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
        return market;
    }


    @Override
    public List<Market> searchMarket(Market market) throws Exception {
        MarketExample example = new MarketExample();
        MarketExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(market.getName())) {
            market.setName("%" + market.getName() + "%");
            criteria.andNameLike(market.getName());
        }
        if (StringUtils.isNotBlank(market.getCity())) {
            criteria.andCityEqualTo(market.getCity());
        }
        example.setOrderByClause(" register_time desc");
        List<Market> marketList = marketMapper.selectByExample(example);

        for (int i = 0; i < marketList.size(); i++) {
            City city = cityService.getCityById(marketList.get(i).getCity());
            if (city != null) {
                marketList.get(i).setCityName(city.getName());
            }
        }
        return marketList;
    }

    @Override
    public InterfaceResult saveOrUpdateMarket(Market market) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        String marketId = market.getId();
        //新增
        if (StringUtils.isBlank(marketId)) {
            MarketExample example = new MarketExample();
            example.createCriteria().andNameEqualTo(market.getName()).andIsvalidEqualTo(1);
            List<Market> markets = marketMapper.selectByExample(example);
            if (markets != null && markets.size() > 0) {
                interfaceResult.InterfaceResult600("市场名已存在");
                return interfaceResult;
            }
            example = new MarketExample();
            example.createCriteria().andMarketNoEqualTo(market.getMarketNo()).andIsvalidEqualTo(1);
            markets = marketMapper.selectByExample(example);
            if (markets != null && markets.size() > 0) {
                interfaceResult.InterfaceResult600("市场编号已存在");
                return interfaceResult;
            }
            marketId = UuidUtils.generateIdentifier();
            market.setId(marketId);
            Market marketParams = updateMarketParams(market);
            count = marketMapper.insertSelective(marketParams);
        } else {//修改
            Market marketParams = updateMarketParams(market);
            count = marketMapper.updateByPrimaryKeySelective(marketParams);
        }
        return interfaceResult;
    }

    @Override
    public boolean updateMarketStatus(String ids, Integer status) throws Exception {
        int count = 0;
        for (String id : ids.split(",")) {
            if (status == -1) {
                count = marketMapper.deleteByPrimaryKey(id);
            } else {
                Market market = marketMapper.selectByPrimaryKey(id);
                if (market != null) {
                    market.setIsvalid(status);
                    count = marketMapper.updateByPrimaryKeySelective(market);
                }
            }
        }
        return count == 1;
    }

    @Override
    public List<Market> selectAll() throws Exception {
        MarketExample marketExample = new MarketExample();
        List<Market> markets = marketMapper.selectByExample(marketExample);
        return markets;
    }

    @Override
    public Market getMarketById(String id) {
        Market market = new Market();
        try {
            market = marketMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
        return market;
    }


    /**
     * 改变物业合同,交易合同,责任书,补充协议 存放的字符串
     *
     * @param market
     * @return
     */
    public Market updateMarketParams(Market market) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(market.getContractProperty(), market.getContractPropertyName())) {
            jsonObject.put("contractPropertyUrl", market.getContractProperty());
            jsonObject.put("contractPropertyName", market.getContractPropertyName());
            market.setContractProperty(jsonObject.toString());
            jsonObject.clear();
        }
        if (StringUtils.isNotBlank(market.getContractTrading(), market.getContractTradingName())) {
            jsonObject.put("contractTradingUrl", market.getContractTrading());
            jsonObject.put("contractTradingName", market.getContractTradingName());
            String contractTradingMap = null;
            String html = HttpClientUtils.sendGet(market.getContractTrading());
            if (StringUtils.isNotBlank(html)) {
                String regex = "\\{.*?\\}";
                Pattern pattern = Pattern.compile(regex);
                Matcher m = pattern.matcher(html);
                JSONArray matchRegexList = new JSONArray();
                while (m.find()) {
                    String str = m.group();
                    matchRegexList.add(str.substring(1, str.length() - 1));
                }
                contractTradingMap = matchRegexList.toJSONString();
            }

            jsonObject.put("contractTradingMap", contractTradingMap);
            market.setContractTrading(jsonObject.toString());
            jsonObject.clear();
        }
        if (StringUtils.isNotBlank(market.getResponsibility(), market.getResponsibilityName())) {
            jsonObject.put("responsibilityUrl", market.getResponsibility());
            jsonObject.put("responsibilityName", market.getResponsibilityName());
            market.setResponsibility(jsonObject.toString());
            jsonObject.clear();
        }
        if (StringUtils.isNotBlank(market.getSupplementary(), market.getSupplementaryName())) {
            jsonObject.put("supplementaryUrl", market.getSupplementary());
            jsonObject.put("supplementaryName", market.getSupplementaryName());
            market.setSupplementary(jsonObject.toString());
            jsonObject.clear();
        }
        return market;
    }
}