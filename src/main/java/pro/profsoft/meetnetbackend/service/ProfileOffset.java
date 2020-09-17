package pro.profsoft.meetnetbackend.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// смещение для листания профилей
public class ProfileOffset {
    private static Map<Long, Integer> offset = new ConcurrentHashMap();

    public static Integer getOffset(Long id) {
        Integer o = offset.get(id);
        return o == null ? 0 : o;
    }

    public static void setOffset(Long id, Integer o) {
        offset.put(id, o);
    }
}
