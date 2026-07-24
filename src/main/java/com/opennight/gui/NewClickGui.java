package com.opennight.gui;

import com.opennight.NightNeo;
import com.opennight.manager.ModuleManager;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.modules.impl.render.ClickGuiModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.ArrayList;
import java.util.List;

public class NewClickGui extends Screen {
    private static final int PANEL_W = 100;
    private static final int BAR_H = 14;
    private static final int ITEM_H = 13;
    private int dragX, dragY, panelX = 10, panelY = 10;
    private boolean dragging;
    private List<Category> cats = new ArrayList<>();
    private List<Module> modules = new ArrayList<>();
    private Category selected = Category.COMBAT;

    public NewClickGui() {
        super(Component.literal("ClickGUI"));
    }

    @Override
    protected void init() {
        cats.clear();
        modules.clear();
        ModuleManager mm = NightNeo.getInstance().getModuleManager();
        for (Category cat : Category.values()) {
            if (!mm.getModulesByCategory(cat).isEmpty()) cats.add(cat);
        }
        refreshModules();
    }

    private void refreshModules() {
        ModuleManager mm = NightNeo.getInstance().getModuleManager();
        modules = mm.getModulesByCategory(selected);
    }

    @Override
    public void render(GuiGraphics graphics, int mx, int my, float pt) {
        renderBackground(graphics, mx, my, pt);

        int catPanelW = 60;
        int catPanelX = panelX - catPanelW - 4;
        int catPanelH = BAR_H + cats.size() * ITEM_H;
        graphics.fill(catPanelX, panelY, catPanelX + catPanelW, panelY + catPanelH, 0xCC202020);

        for (int i = 0; i < cats.size(); i++) {
            Category cat = cats.get(i);
            int y = panelY + BAR_H + i * ITEM_H;
            boolean sel = cat == selected;
            if (sel) graphics.fill(catPanelX, y, catPanelX + catPanelW, y + ITEM_H, 0x40FFFFFF);
            graphics.drawString(font, cat.displayName, catPanelX + 3, y + 2, sel ? 0xFFFFFF : 0xAAAAAA);
            if (sel && isHovered(catPanelX, y, catPanelW, ITEM_H, mx, my) && clicked()) {
                selected = cat;
                refreshModules();
            }
        }

        int panelH = Math.min(BAR_H + modules.size() * ITEM_H, 300);
        graphics.fill(panelX, panelY, panelX + PANEL_W, panelY + panelH, 0xCC202020);
        graphics.fill(panelX, panelY, panelX + PANEL_W, panelY + BAR_H, 0xFF404040);
        graphics.drawCenteredString(font, selected.displayName, panelX + PANEL_W / 2, panelY + 3, 0xFFFFFF);

        if (dragging) {
            panelX = mx - dragX;
            panelY = my - dragY;
        }
        if (isHovered(panelX, panelY, PANEL_W, BAR_H, mx, my) && mouseDown && lastMb == 0) {
            dragging = true;
            dragX = mx - panelX;
            dragY = my - panelY;
        }

        for (int i = 0; i < modules.size(); i++) {
            Module m = modules.get(i);
            int y = panelY + BAR_H + i * ITEM_H;
            if (y + ITEM_H > panelY + panelH) break;
            boolean enabled = m.isEnabled();
            if (enabled) graphics.fill(panelX, y, panelX + PANEL_W, y + ITEM_H, 0x30FFFFFF);
            graphics.drawString(font, m.getName(), panelX + 3, y + 2, enabled ? 0x55FF55 : 0xCCCCCC);
            if (isHovered(panelX, y, PANEL_W, ITEM_H, mx, my) && clicked()) {
                m.toggle();
            }
        }
    }

    private boolean mouseDown;
    private int lastMb;

    private boolean clicked() {
        return mouseDown && lastMb == 0;
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        mouseDown = true;
        lastMb = button;
        return super.mouseClicked(mx, my, button);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int button) {
        mouseDown = false;
        dragging = false;
        return super.mouseReleased(mx, my, button);
    }

    private boolean isHovered(int x, int y, int w, int h, int mx, int my) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    @Override
    public void onClose() {
        super.onClose();
        ClickGuiModule.INSTANCE.isOpen = false;
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
