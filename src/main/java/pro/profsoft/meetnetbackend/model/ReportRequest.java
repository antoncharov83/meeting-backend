package pro.profsoft.meetnetbackend.model;

import java.util.List;

enum Reason {
    UNACCEPTABLE_CONTENT, // Недопустимый контент
    FAILURE_CONTENT,      // Некачественный контент
    UNMATCHTABLE_CONTENT  // Фото не соответствует действительности
}

public class ReportRequest {
    private Long reportResourceId;      // id медиа ресурса
    private List<Integer> reportReason; // массив причин
}
