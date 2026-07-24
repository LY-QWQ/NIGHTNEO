package com.opennight.render;

import com.opennight.render.GlyphPage;

record Glyph(int u, int v, int width, int height, char value, GlyphPage owner) {
}