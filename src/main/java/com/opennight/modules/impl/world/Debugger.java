package com.opennight.modules.impl.world;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashSet;

import com.opennight.event.impl.DisconnectEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.utils.misc.ChatUtil;
import com.opennight.event.EventTarget;

public class Debugger
extends Module {
    public Debugger() {
        super("Debugger", Category.WORLD);
    }

    @EventTarget
    public void onDisconnect(DisconnectEvent disconnectEvent) {
        HashSet<String> suspiciousClasses = new HashSet<>();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        if (threadMXBean == null) {
            return;
        }
        ThreadInfo[] threads = threadMXBean.dumpAllThreads(false, false);
        int count = 0;
        for (ThreadInfo threadInfo : threads) {
            String threadName = threadInfo.getThreadName();
            StackTraceElement[] stackTrace = threadInfo.getStackTrace();
            if (threadName == null || stackTrace == null) continue;
            for (StackTraceElement stackTraceElement : stackTrace) {
                String className = stackTraceElement.getClassName();
                String fileName = stackTraceElement.getFileName();
                String moduleName = stackTraceElement.getModuleName();
                if (fileName != null || moduleName != null) continue;
                suspiciousClasses.add(className);
                ++count;
            }
        }
        ChatUtil.print("N: " + count + ", Set: ");
        ChatUtil.print("==========================");
        for (String className : suspiciousClasses) {
            ChatUtil.print(className);
        }
        ChatUtil.print("==========================");
    }
}