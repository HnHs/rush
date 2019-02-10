# TOKENS

```rush
//
long func_long (long x long y) {
	int x = 2
	func(2 x)
}

func(1 2)
```

|Key                        |Token              |
|---                        |---                |
|long                       |LONG_TOKEN         |
|string                     |STRING_TOKEN       |
|var                        |VAR_TOKEN          |
|(                          |OPEN_PAREN_TOKEN   |
|)                          |CLOSE_PARAN_TOKEN  |
|{                          |OPEN_BRACE_TOKEN   |
|}                          |CLOSE_BRACE_TOKEN  |
|=                          |EQUAL_TOKEN        |
|+                          |PLUS_TOKEN         |
|-                          |MINUS_TOKEN        |
|\/\/[\w ]*[\n]*            |COMMENT_TOKEN      |
|/                          |DIV_TOKEN          |
|*                          |MULT_TOKEN         |
|^[a-zA-Z_]{1}[a-zA-Z0-9_]* |IDENTIFIER_TOKEN   |
|^[\n\r]+                   |LINE_BREAKER_TOKEN |
|^[\t ]+                    |WHITE_SPACE_TOKEN  |




