# cpp-coverage
Netbeans plugin for displaying C++ Coverage

# How to use

In Makefile in section [run tests] target[test: .test-post] you need to add report generating

    lcov --directory build/Debug/GNU-Linux/src/ --capture --output-file report.info
