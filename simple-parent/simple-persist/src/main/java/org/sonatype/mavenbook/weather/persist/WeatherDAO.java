package org.sonatype.mavenbook.weather.persist;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.sonatype.mavenbook.weather.model.Location;
import org.sonatype.mavenbook.weather.model.Weather;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDAO extends HibernateDaoSupport {

    public WeatherDAO() {}

    public void save(Weather weather) {
        getHibernateTemplate().save(weather);
    }

    public Weather load(Integer id) {
        return (Weather) getHibernateTemplate().load(Weather.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Weather> recentForLocation(final Location location) {
        return (List<Weather>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
                        Query query = getSession().getNamedQuery("Weather.byLocation");
                        query.setParameter("location", location);
                        return new ArrayList<Weather>(query.list());
                    }
                }
        );
    }
}
