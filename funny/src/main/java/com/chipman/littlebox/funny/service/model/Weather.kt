package com.chipman.littlebox.funny.service.model

/**
 * 今日天气信息
 * "address": "广东省 深圳市",
 * "cityCode": "440300",
 * "temp": "18℃",
 * "weather": "小雨",
 * "windDirection": "东北",
 * "windPower": "≤3级",
 * "humidity": "92%",
 * "reportTime": "2018-11-27 22:40:53"
 */
data class TodayWeather(
    val address: String,            // 城市具体信息，比如 “广东省 深圳市”
    val cityCode: String,           // 城市code
    val temp: String,               // 温度值
    val weather: String,            // 天气描述，具体描述请查看附件，天气描述清单
    val windDirection: String,      // 风向描述，具体描述请查看附件，风向表清单
    val windPower: String,          // 	风力描述，具体描述请查看附件，风力表清单
    val humidity: String,           // 湿度值
    val reportTime: String          // 此次天气发布时间
)


/**
 * 今天及未来天气信息
 */
data class ForecastWeather(
    val address: String,
    val cityCode: String,
    val forecasts: List<Forecast>,      // 今天及未来天气列表
    val reportTime: String
) {
    data class Forecast(
        val date: String,               // 日期
        val dayOfWeek: String,          // 星期
        val dayTemp: String,            // 白天温度
        val dayWeather: String,         // 白天天气描述
        val dayWindDirection: String,   // 白天风向
        val dayWindPower: String,       // 白天风力
        val nightTemp: String,          // 晚上温度
        val nightWeather: String,       // 晚上天气描述
        val nightWindDirection: String, // 晚上风向
        val nightWindPower: String      // 晚上风力
    )
}