package org.sonatype.mavenbook.web;

import org.sonatype.mavenbook.weather.WeatherService;
import org.sonatype.mavenbook.weather.model.Weather;
import org.sonatype.mavenbook.weather.persist.WeatherDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeatherController implements Controller {

    private WeatherService weatherService;
    private WeatherDAO weatherDAO;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String zip = request.getParameter("zip");
        Weather weather = weatherService.retrieveForecast(zip);
        weatherDAO.save(weather);
        return new ModelAndView("weather", "weather", weather);
    }

    public WeatherService getWeahterService() {
        return weatherService;
    }

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public WeatherDAO getWeatherDAO() {
        return weatherDAO;
    }

    public void setWeatherDAO(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }
}
