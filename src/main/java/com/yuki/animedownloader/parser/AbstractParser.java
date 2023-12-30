package com.yuki.animedownloader.parser;

import java.util.List;

public abstract class AbstractParser<S, T> {

    public abstract List<T> parse(S data);
}
