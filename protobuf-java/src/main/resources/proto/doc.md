
> 生成 Java 命令

```
protoc -I=src/main/resources/proto --java_out=src/main/java wind.proto
```

> 生成 JavaScript 命令

```
protoc -I=src/main/resources/proto --js_out=import_style=commonjs,binary:. wind.proto
```


