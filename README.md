# Hack Virtual Machine: VM to Jack (Tokenizer)

## Parses one or more Jack (.jack) files in a given directory, matching the code input to the Jack language grammar specification. Outputs tokenized XML file(s) to the same directory.

This programme handles the unfinished first stage of the Jack to VM compilation process. The final process outputs Virtual Machine (.vm) language rather than XML.

This personal project has been created as an accompaniment for Chapter 9 of the book [The Elements of Computing Systems](https://www.nand2tetris.org/course). The aforementioned should be referenced for the full specification of the Hack VM and Jack. The Hack VM code is designed to run on the Hack Hardware platform.

An overview of the project, classes and features is described below.

---

# VM Compiler

#### The `VMCompiler` class serves as the software entry point.

Loads Jack source file(s) and initiates the Tokenizer which begins file parsing and writing.

# Packages

## `tokenizer`

#### Parsing & writing files

The `VMCompiler` takes one or more Jack (.jack) files as input which are passed to the `tokenizer`. The `tokenizer` marches through each file, disecting lines code to individual tokens and checking against the Jack language grammar. Assuming no faults are found, XML file(s) containing tokenized output are written to the same directory with file name(s) to match the input filen ames.

## `tokenlib`:

Library of all constant tokens in the Jack language as well as unique `stringConstant`s and `integerConstant`s encountered by the `tokenizer`.

Tokens can match one of the following types outlined below. Full specification of the Jack language [here](https://www.nand2tetris.org/course).

- `KEYWORD`
  One of the following constants: `class`, `constructor`, `function`, `method`, `field`, `static`, `var`, `int`, `char`, `boolean`, `void`, `true`, `false`, `null`, `this`, `let`, `do`, `if`, `else`, `while`, `return`.

- `SYMBOL`
  One of the following constants: `{`, `}`,`(`,`)`,`[`,`]`,`,`,`.`,`;`,`+`,`-`,`*`,`/`,`&`,`|`,`<`,`>`,`=`,`_`,`~`.

- `IDENTIFIER`
  A sequence of letters, digits and underscore not starting with a digit

- `INT_CONST`
  A decimal number in the range 0-32767

- `STRING_CONST`
  A sequence of Unicode characters not including double quote or new line enclosed in double quotation marks (`"`)


## `token`

Classes for the token types described above with a range of helper functions.

## `compilationengine`:

This class is the main driver for language grammar matching. the inbuilt `parseToken` method is called once for every token encountered by the `tokenizer`.

While the `tokenizer` marches through the Jack programme and feeds tokens to the `compilationEngine`, the `compilationEngine` internally registers and handles the programme position using built-in subclasses.

As an example, with `class` as the entry point for all Jack programmes, `CompileClass` is the first routine called and begins by matching the given input to the following tokens in order `class`, `identifier`, `{`.

Following this, a variable declaration may be specified; if a `var` token is encountered, the `CompileVarDec` subclass is initiated, all succeeding tokens must match the `varDec` grammar (`var TOKEN IDENTIFIER ... ;` unil this subroutine is complete.

Subsequently, a series of Jack subroutines may be initiated within the `CompileSubroutineDec` class. Relevant subclasses will run recursively, parsing statements, expressions and terms until all are complete and the initial `CompileClass` routine has finished.

# Loading files

The `loadfiles` package contains a utility class for importing files of a specific type within a given directory.

# Tests

Unit tests can be found in the `/tests` directory.
