<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>

</head>

<body style="background: white;
           width:790px;
           margin:0 auto;padding: 0;">
    <!-- 官方认证 -->
    <div style="width: 100%;
            border-top: 1px solid #ccc;">
        <img src="https://img.alicdn.com/imgextra/i2/4052462357/O1CN011THXzFmVHPI5yfW_!!4052462357.jpg">
       		<div style="text-align: center;color: #1B2EDB;font-size: 28px;padding: 20px 0;font-weight: bold;">
				<p style="margin: 10px 0;">
                   玉林阿里智慧汽车城
				</p>
                <p style="margin: 10px 0;">
                ${客户名称}
                </p>
				<p>0775-2552986</p>
			</div>
    </div>
    <!-- 检测报告
  -->
            <div style="width: 790px">
        <ul style=" width: 368px;
            height: 40px;
            line-height: 40px;
            margin:auto;
            margin-top:50px;
            margin-bottom:70px;
            font-size: 24px;
            background-color: #1B2EDB;
            border-radius: 24px;
            text-align: center;
            color: white;padding: 0;list-style: none;">
            <li style="float: left;
            display: inline-block;
            background-color: #FF005C;
            border-radius: 18px;
            width: 132px;
            padding: 0 10px;list-style: none;">专业检测</li>
            <li style="list-style: none;font-size: 18px;">维真  &nbsp 专业检测报告</li>
        </ul>
        <header style="position: relative;">
        </header>
        <div style="padding: 5% 0 0 0;">
            <div style="
                width: 170px;
                margin-right: 30px;
                display: inline-block;
                padding: 20px 10px;
                text-align: center;
                border: 3px solid #666;
                height: 1350px;
                ">
                <div style="padding: 20px 0;">
                    <#list mainPic as pic>
                    <img style="width: 100%;" src="${pic}" alt="">
                    </#list>
                </div>
                 <div style="padding: 20px 0;font-size: 12px;">
                    <p style="font-size: 15px;font-weight: 600">vin:${vin}</p>
                </div>
                <ul style="padding: 0;margin-top: 60px;font-size: 12px;font-weight: bold;">
                    <li style="list-style: none;height: 50px;line-height: 1;">
                        严格遵守
                    </li>
                    <li style="list-style: none;height: 50px;line-height: 1;">
                        车辆检测标准
                    </li>
                    <li style="list-style: none;height: 50px;line-height: 1;">
                        符合国家检测
                    </li>
                    <li style="list-style: none;height: 50px;line-height: 1;">
                        标准GB/T 30323-2013
                    </li>
                </ul>
                <div style="padding-bottom: 70px;">
                    <p style="font-size: 12px;height: 20px;line-height: 1;margin: 40px 0;font-weight: bold;">客户名称</p>
                    <p style="font-size: 12px;height: 20px;line-height: 1;margin: 40px 0;font-weight: bold;">${客户名称}</p>
                </div>
                <div style="padding-bottom: 50px;">
                    <p style="font-size: 12px;height: 20px;line-height: 1;font-weight: bold;">检测时间</p>
                    <p style="font-size: 12px;height: 20px;line-height: 1;font-weight: bold;">${检测时间}</p>
                </div>

            </div>
            
            <div style="width: 62%;
                display: inline-block;
                vertical-align: top;
                ">
                <div>
                    <div style="border-bottom: 2px dashed #d7d7d7;">
                        <img src="https://img.alicdn.com/imgextra/i2/3817124563/TB2kl2KxQyWBuNjy0FpXXassXXa-3817124563.png" alt="">
                        <span style="margin-left: 10px;font-size: 20px;font-weight: 600;height: 60px;display: inline-block;vertical-align: middle;">基本信息</span>
                    </div>
                    <table cellpadding="0" style="width: 100%;padding-top: 10px;">
                    <tbody>
                            <tr  style="font-size: 14px;font-weight: bold;color: #b7b7b7">
                                <td style="height: 40px;line-height: 40px;">品牌</td>
                                <td style="height: 40px;line-height: 40px;">车身颜色</td>
                                <td style="height: 40px;line-height: 40px;">生产日期</td>
                                <td style="height: 40px;line-height: 40px;">表显里程</td>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr style="font-size: 16px;font-weight: bold;">
                                <#if brandname != "">
                                <td  style="height: 40px;line-height: 40px;">${brandname}</td>
                                </#if>
                                <#if color != "">
                                <td  style="height: 40px;line-height: 40px;">${color}</td>
                                </#if>
                                <#if 出厂日期 != "">
                                <td  style="height: 40px;line-height: 40px;">${出厂日期}</td>
                                </#if>
                                <#if mileage != "">
                                <td  style="height: 40px;line-height: 40px;">${mileage}万公里</td>
                                </#if>
                            </tr>
                        </tbody>
                        
                    </table>
                </div>
                <div style="margin-top: 10px;">
                    <div style="border-bottom: 2px dashed #d7d7d7;">
                        <img src="https://img.alicdn.com/imgextra/i1/3817124563/TB2ISuApByWBuNkSmFPXXXguVXa-3817124563.png" alt="">
                        <span style="margin-left: 10px;font-size: 20px;font-weight: 600;height: 60px;display: inline-block;vertical-align: middle;">事故车排查鉴定</span>
                    </div>
                    <div style="text-align: center;position: relative;">
                        <ul style="display: inline-block;width: 30%;padding: 20px 0;vertical-align: top;font-size: 16px;margin: 0;">
                            <li style="list-style: none">
                                <img style="width: 50%;" src="https://img.alicdn.com/imgextra/i1/4052462357/O1CN011THXzKtAvnGneVF_!!4052462357.png" alt="">
                            </li>
                            <li style="list-style: none">排除重大碰撞</li>
                        </ul>
                        <ul style="display: inline-block;width: 30%;padding: 20px 0;vertical-align: top;font-size: 16px;margin: 0;">
                            <li style="list-style: none">
                                <img style="width: 50%;" src="https://img.alicdn.com/imgextra/i1/4052462357/O1CN011THXzJH8cjBovEu_!!4052462357.png" alt="">
                            </li>
                            <li style="list-style: none">排除火烧</li>
                        </ul>
                        <ul style="display: inline-block;width: 30%;padding: 20px 0;vertical-align: top;font-size: 16px;margin: 0;">
                            <li style="list-style: none">
                                <img style="width: 50%;" src="https://img.alicdn.com/imgextra/i1/4052462357/O1CN011THXzJH91gjPl4x_!!4052462357.png" alt="">
                            </li>
                            <li style="list-style: none">排除水泡</li>
                        </ul>
                    </div>
                </div>
                <div>    
                <div style="border-bottom: 2.0px dashed #d7d7d7;">     <span style="margin-left: 10.0px;font-size: 18.0px;font-weight: 600;height: 40.0px;display: inline-block;vertical-align: middle;"><span style="margin-left: 10.0px;font-size: 18.0px;font-weight: 600;height: 40.0px;display: inline-block;vertical-align: middle;"><span style="font-size: 18.0px;"><strong><span style="color: #ff0000;"><span style="margin-left: 10.0px;font-weight: 600;height: 40.0px;display: inline-block;vertical-align: middle;">此车经过维真验车初检，全面检测请致电4009970017</span></span></strong></span></span></span>    </div>    
                   
                </div>
                <div>
                     <div style="border-bottom: 2px dashed #d7d7d7;">
                        <span style="margin-left: 10px;font-size: 18px;font-weight: 600;height: 40px;display: inline-block;vertical-align: middle;">车身骨架检查</span>
                    </div>
                    
                         <table cellpadding="0" cellspacing="0" style="width: 550px;padding-top: 10px;font-size: 10px;font-weight: bold;">
                            <thead>
                                <tr>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${车体对称性}' == '对称'>
                                        	<img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt="">
                                        <#else>
                                        	<img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/>
                                        </#if>
                                        <span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">车体对称性</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${左A柱}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if>
                                        <span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">左A柱</span>
                                    </td>
                                    
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${左B柱}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if>
                                        <span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">左B柱</span>
                                    </td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${左C柱}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if>
                                        <span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">左C柱</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${右A柱}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if>
                                        <span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">右A柱</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${右B柱}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">右B柱</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${右C柱}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">右C柱</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${左侧纵梁}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">左侧纵梁</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${右侧纵梁}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">右侧纵梁</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${左前减震器悬挂部位}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">左前减震器悬挂部位</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${右前减震器悬挂部位}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">右前减震器悬挂部位</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${左后减震器悬挂部位}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">左后减震器悬挂部位</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${右前减震器悬挂部位}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">右前减震器悬挂部位</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${左后减震器悬挂部位}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">左后减震器悬挂部位</span>
                                    </td>
                                    <td style="height: 30px;padding: 0 5px;width: 160px;">
                                        <#if '${右后减震器悬挂部位}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 4px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">右后减震器悬挂部位</span>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                </div>
                <div style="margin-top: 10px;">
                    <div style="border-bottom: 2px dashed #d7d7d7;">
                        <span style="margin-left: 10px;font-size: 18px;font-weight: 600;height: 40px;display: inline-block;vertical-align: middle;">水泡车检查</span>
                    </div>
                    <div>
                        <table cellpadding="0" cellspacing="0" style="width: 550px;padding-top: 10px;font-size: 10px;font-weight: bold;">
                            <thead>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${发动机表面}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">发动机表面</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${防火墙}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">防火墙</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${线束插头}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">线束插头</span>
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${线束}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">线束</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${减震器座}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">减震器座</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${保险丝盒}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">保险丝盒</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${转向柱}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">转向柱</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${座椅导轨}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">座椅导轨</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${座椅插头}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">座椅插头</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${座椅底部}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">座椅底部</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${座椅皮质}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">座椅皮质</span>
                                </td>

                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${地胶}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">地胶</span>
                                </td>

                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${车底线束}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">车底线束</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${点烟器}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">点烟器</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${门饰板内部}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">门饰板内部</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${顶棚}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">顶棚</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${安全带卡扣}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">安全带卡扣</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${安全带}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">安全带</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${仪表台骨架}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">仪表台骨架</span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div style="margin-top: 10px;">
                    <div style="border-bottom: 2px dashed #d7d7d7;">
                        <span style="margin-left: 10px;font-size: 20px;font-weight: 600;height: 40px;display: inline-block;vertical-align: middle;">火烧车检查</span>
                    </div>
                    <div>
                        <table cellpadding="0" cellspacing="0" style="width: 550px;padding-top: 10px;font-size: 10px;font-weight: bold;">
                            <thead>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${发动机整体}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">发动机整体</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${防火墙}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">防火墙</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${发动机隔音棉}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">发动机隔音棉</span>
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${机舱线束}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">机舱线束</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${减震器座}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">减震器座</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${仪表台及骨架}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">仪表台及骨架</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${车内电器}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">车内电器</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${座椅}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">座椅</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${地毯}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">地毯</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${全车内饰}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">全车内饰</span>
                                </td>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${全车玻璃}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">全车玻璃</span>
                                </td>

                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${后备箱两侧内衬}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">后备箱两侧内衬</span>
                                </td>

                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${后备箱整体情况}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">后备箱整体情况</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="height: 30px;padding: 0 5px;width: 160px;">
                                    <#if '${全车胶条}' == '正常'><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB2A3LdxNSYBuNjSsphXXbGvVXa-3817124563.png" alt=""><#else><img style="width: 10%;vertical-align: top;margin-top: 6px;" src="https://img.alicdn.com/imgextra/i1/3817124563/TB20xrrxL5TBuNjSspcXXbnGFXa-3817124563.png" alt=""/></#if><span style="margin-left: 5px;width: 80%;display: inline-block;padding: 5px 0;height: 34px;">全车胶条</span>
                                </td>

                            </tr>

                            </tbody>
                        </table>
                    </div>
        </div>
        
    </div>
    <div style="width: 100%;">
         <ul style=" width: 368px;
            height: 40px;
            line-height: 40px;
            margin:auto;
            margin-top:50px;
            margin-bottom:70px;
            font-size: 24px;
            background-color: #1B2EDB;
            border-radius: 24px;
            text-align: center;
            color: white;padding: 0;list-style: none;">
            <li style="float: left;
            display: inline-block;
            background-color: #FF005C;
            border-radius: 18px;
            width: 132px;
            padding: 0 10px;list-style: none;">车城展示</li>
            <li style="list-style: none;font-size: 18px;">全方位车城展示</li>
        </ul>
        <div>
            <img src="https://img.alicdn.com/imgextra/i2/4052462357/TB2sL.yb3HqK1RjSZFEXXcGMXXa_!!4052462357.jpg" alt="">
            <img style="margin-left: -5px" src="https://img.alicdn.com/imgextra/i4/4052462357/TB2.qAyb4TpK1RjSZFKXXa2wXXa_!!4052462357.jpg" alt="">
        </div>
    </div>
    <div style="width: 100%;">
    <ul style=" width: 368px;
            height: 40px;
            line-height: 40px;
            margin:auto;
            margin-top:50px;
            margin-bottom:70px;
            font-size: 24px;
            background-color: #1B2EDB;
            border-radius: 24px;
            text-align: center;
            color: white;padding: 0;list-style: none;">
            <li style="float: left;
            display: inline-block;
            background-color: #FF005C;
            border-radius: 18px;
            width: 132px;
            padding: 0 10px;list-style: none;">车辆展示</li>
            <li style="list-style: none;font-size: 18px;">全方位展示车辆</li>
        </ul>
        <div>
            <#list aspectList as show>
            <img style="width:100%;height:auto" src="${show}">
            </#list>
        </div>
        <div>
            <#list interiorList as interior>
                <img style="width:100%;height:auto" src="${interior}">
            </#list>
        </div>
    </div>
</body>
</html>
