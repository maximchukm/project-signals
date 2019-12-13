package io.signal.test;

import org.springframework.context.annotation.ComponentScan;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@ComponentScan(basePackages = {"io.signal.test.transmitter", "io.signal.test.receiver"})
public class TestConfiguration {}