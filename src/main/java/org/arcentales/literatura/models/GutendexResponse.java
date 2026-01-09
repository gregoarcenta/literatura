package org.arcentales.literatura.models;

import java.util.List;

public record GutendexResponse(int count, String next, String previous, List<BookData> results) {}
