Chronicle Queue persists every single message using a memory-mapped file. This allows us to share messages between processes.

It stores data directly to off-heap memory, therefore, making it free of GC overhead. It is designed for providing low-latency message framework for high-performance applications.

https://www.baeldung.com/java-chronicle-queue