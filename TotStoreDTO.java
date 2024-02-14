package cue.edu.co.mapping;

import java.lang.reflect.Type;

public record ToyStoreDTO<type>(String name, Type type , Integer prize, Integer amount ) {
}
