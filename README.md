# InterroBench Problem-Set

This is a problem-set for the [SherlockBench AI benchmarking system](https://sherlockbench.com).

It is a port of the version 6 problem-set from the old [InterroBench benchmark](https://interrobench.com/).

## Usage
Copy the source file into your checkout of the
[SherlockBench-API](https://github.com/Xylon2/sherlockbench-api) under `src/clj/`.

Then edit `config.edn` and add it to `:problem-namespaces`.

## Development
It's just a single file.

Run the tests like this:
```
clj -X:test
```
