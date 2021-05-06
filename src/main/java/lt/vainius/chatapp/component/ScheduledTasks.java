package lt.vainius.chatapp.component;

import lt.vainius.chatapp.service.AttachmentService;
import lt.vainius.chatapp.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private Util util;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    //Every hour xx:30:00
    @Scheduled(cron = "0 30 * * * *")
    public void deleteOldAttachments() {
        logger.info("Scheduled job deleteOldAttachments started.");
        attachmentService.deleteOldAttachments(util.subtractOneHourFromCurrentTime());
    }
}
