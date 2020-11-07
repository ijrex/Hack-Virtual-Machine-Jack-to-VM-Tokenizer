# Hack Virtual Machine: VM to Jack (Tokenizer)

## Parses one or more Jack (.jack) files in a given directory, checking the code input against the Jack language grammar specification. Outputs tokenized XML file(s) to the same location.

This programme handles the unfinished first stage of the Jack to VM compilation process. The complete programme outputs Virtual Machine (.vm) code designated for the the Hack Virtual Machine rather than tokenized XML.

This personal project has been created as an accompaniment for Chapter 9 of the book [The Elements of Computing Systems](https://www.nand2tetris.org/course). The aforementioned should be referenced for the full specification of the Hack VM and Jack. The Hack VM code is designed to run on the Hack Hardware platform.

An overview of the project, classes and features is described below.

---

# VM Compiler

#### The `VMCompiler` class serves as the software entry point.

Loads Jack source file(s) and initiates the Tokenizer which handles file parsing/writing using the inbuilt Compilation Engine.

# Packages

### `tokenizer`

#### Handles parsing/writing files

The Tokenizer accepts input files from the VM Compiler. This module marches through each file, sorting lines of Jack code into individual tokens while checking against the Jack language grammar. Assuming no faults are found, XML made-up of each token in the programme is written to the same directory with matching filename(s) to the input files.

### `tokenlib`:

#### Library of all constant tokens in the Jack language as well as unique string and integer constants encountered by the Tokenizer.

Tokens can match one of the following types outlined below. Full specification of the Jack language [here](https://www.nand2tetris.org/course).

- ###### KEYWORD
  Any of the following constants: `class`, `constructor`, `function`, `method`, `field`, `static`, `var`, `int`, `char`, `boolean`, `void`, `true`, `false`, `null`, `this`, `let`, `do`, `if`, `else`, `while`, `return`

- ###### SYMBOL
  Any of the following constants: `{`, `}`,`(`,`)`,`[`,`]`,`,`,`.`,`;`,`+`,`-`,`*`,`/`,`&`,`|`,`<`,`>`,`=`,`_`,`~`

- ###### IDENTIFIER
  A sequence of letters, digits and underscores not starting with a digit

- ###### INTEGER CONSTANT
  A decimal number in the range 0-32767

- ###### STRING CONSTANT
  Enclosed in double quotation marks (`"`) - a sequence of Unicode characters not including a double quote or new line.


### `token`

#### Classes for the token types described above with a range of helper functions.

### `compilationengine`

#### The main driver for language grammar matching. The inbuilt `parseToken` method is called once for every token encountered by the Tokenizer.

While the Tokenizer marches through the Jack programme and feeds tokens to the Compilation Engine, the Compilation Engine internally registers and handles the programme position using built-in subclasses.

As an example, with `class` as the entry point for all Jack programmes; `CompileClass` is the first routine called by the Compilation Engine. It begins matching the given input tokens in the following order, `class`, `identifier`, `{`.

Following this, a variable declaration may be specified; if a `var` token is encountered, the `CompileVarDec` subclass is initiated and all succeeding tokens must match the `varDec` grammar (`var TOKEN IDENTIFIER ... ;`) unil this subroutine is complete.

Subsequent to the variable declarations, a series of Jack subroutines may be encountered and handled within the Engine's `CompileSubroutineDec` class. Relevant subclasses will run recursively, parsing statements, expressions and terms until all are complete and the initial `CompileClass` routine has finished.

# Loading files

#### The `loadfiles` package contains a utility class for importing files of a specific type within a given directory.

# Development mode

The recursive nature of the Compilaton Engine makes it fairly difficult to develop for. Subroutines rely on nested subroutines with unknown depths and do not know in advance when they will complete.

A `development = true` setting is available in the constructor for all Compile classes. This mode lets the compiler subroutine 'force close' gracefully rather than throw an error if incomplete.

As an example - `CompileStatementWhile` expects the grammar `while` `(` `expression` `)` `{` `statements` `}`. During development, we may only have the first half of this statement built (`while` `(` `expression` `)` `{`).

By setting `develop` on `CompileStatementWhile` and its parent classes - the programme will only attempt to parse so far and then 'force close' itself and its parent routines without throwing an error. Force closed XML tags are flagged with a trailing `.`.

```xml
<class>
 ..
 <subroutineBody>
 ..
  <statements>
   <whileStatement>
    <keyword> while </keyword>
    <symbol> ( </symbol>
    <expression>
     <term>
      <identifier> true </identifier>
     </term>
     </expression>
     <symbol> ) </symbol>
     <symbol> { </symbol>
     <!-- Above this line is parsed code -->
     <!-- Below this line, the engine 'force closed' rather than throwing an error -->
   </whileStatement .>
  </statements . >
 </subroutineBody . >
</class . >
...
...
```

# Tests

#### XML files with the expected output from the compiler have the `.test.xml` suffix and are found in the `/tests/**/*` subdirectories.

Output files from the compiler can be checked against the `.test.xml` files using a [text comparison tool](https://www.nand2tetris.org/software).

- `/ArrayTest`, `/ExpressionLessSquare` and `/Square` test the tokenized output for basic Jack programmes.

- `/Grammar` more thoroughly tests the tokenized output for the Jack language specification.

