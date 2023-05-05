package com.nhnacademy.todo.repository.impl;

import com.nhnacademy.todo.domain.Event;
import com.nhnacademy.todo.repository.EventRepository;
import com.nhnacademy.todo.share.UserIdStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Primary
@Repository
@RequiredArgsConstructor
public class DbEventRepository implements EventRepository {

    private final DataSource dataSource;

    @Override
    public Event save(Event event) {
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Event result = null;
        try{
            connection = dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        try{
            connection.setAutoCommit(false);

            String sql = "insert into event(subject,user_id,event_at) values(?,?,?)";
//            String sql2 = "inset into event set subject=?,user_id=?,event_at=? created_at=now()";
            //sql injection
            int index=0;
            psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(++index,event.getSubject());
            psmt.setString(++index, UserIdStore.getUserId());
            psmt.setObject(++index,event.getEventAt());
            psmt.executeUpdate();

            //select_last_id()
            rs = psmt.getGeneratedKeys();
            connection.commit();
            if(rs.next()){
                long eventId = rs.getLong(1);
                result = getEvent(eventId);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally{
            if(Objects.nonNull(rs)){
                try{
                    rs.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
            if(Objects.nonNull(psmt)){
                try{
                    psmt.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
            if(Objects.nonNull(connection)){
                try{
                    connection.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    @Override
    public void update(Event event) {
    }

    @Override
    public void deleteById(String userId, long eventId) {
    }

    @Override
    public Event getEvent(String userId, long eventId) {
        return null;
    }
    @Override
    public Event getEvent(long eventId) {
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try{
            connection = dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        String sql = "select id,subject,user_id,event_at,created_at from event where id=?";
        try{
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1,eventId);
            rs = psmt.executeQuery();
            if(rs.next()){
                LocalDate localDate = rs.getDate("event_at").toLocalDate();
                Event event = new Event(rs.getString("user_id"),rs.getString("subject"),localDate);
                event.setId(rs.getLong("id"));
                return event;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally{
            if(Objects.nonNull(rs)){
                try{
                    rs.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
            if(Objects.nonNull(psmt)){
                try{
                    psmt.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
            if(Objects.nonNull(connection)){
                try{
                    connection.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public List<Event> findAllByDay(String userId, int year, int month, int day) {
        return null;
    }

    @Override
    public List<Event> findAllByMonth(String userId, int year, int month) {
        return null;
    }

    @Override
    public long countByUserIdAndEventAt(String userId, LocalDate targetDate) {
        return 0;
    }

    @Override
    public void deletebyDaily(String userId, LocalDate targetDate) {

    }

    @Override
    public void init() {

    }
}
