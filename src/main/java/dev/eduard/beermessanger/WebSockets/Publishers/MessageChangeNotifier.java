package dev.eduard.beermessanger.WebSockets.Publishers;

import dev.eduard.beermessanger.models.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MessageChangeNotifier {
    private final Collection<Listener<Message>> listeners = new ArrayList<>();

    public void notifyChange(Message message) {
        listeners.forEach(listener -> listener.onEntityChange(message));
    }
    public void registerListener(Listener<Message> listener){
        listeners.add(listener);
    }


    interface Listener<T> {
        void onEntityChange(T entity);
    }
}
