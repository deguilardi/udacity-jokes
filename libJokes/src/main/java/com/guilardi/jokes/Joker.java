package com.guilardi.jokes;

public class Joker {

    private final static String[] mJokes = {
            "I can't believe I made it anywhere creatively, though, because I was raised by two loving and supportive parents. Nothing squashes creativity more than unconditional love and support from a functional household. If you have kids, sh*t on their dreams a little bit.",
            "A bear walks into a bar and says to the bartender, \"I'll have a pint of beer and a.......... packet of peanuts. The bartender asks, \"Why the big pause?\"",
            "Q: Why did the chewing gum cross the road? A: He was stuck to the chicken's foot."
    };

    public static String getJoke(Integer id) {
        return mJokes[id];
    }
}
