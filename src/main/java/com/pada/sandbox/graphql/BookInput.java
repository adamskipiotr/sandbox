package com.pada.sandbox.graphql;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record BookInput(String id, String name, List<String> chapters, int pageCount){

}