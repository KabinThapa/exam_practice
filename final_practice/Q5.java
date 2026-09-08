/*
Socket programming is the concept of using sockets(port+IP address) to communicate
between two application or devices across network. A socket is an API allowing us to access
the internet and share data in the internet.
in java socket hides all the underlying hassle of binding, listening to the incoming signals
and provide a standard API through java.net package with ServerSocket and Socket API calls.

TCP(Transimission Control Protocol) is a standard protocol used in networking
which ensures reliable delivery and security. It is a connection oriented protocol and uses acknowledgement for reliability
and handshaking for security. As this is connection oriented, it's relatively slower than UDP

UDP(User Datagram Protocol) is another standard protocol, that is connection less and fast
it communicates with other devices and application through datapackets. It doesn't ensure
reliability nor security. it generally shouts in the internet, here is the packet take it if you want it

both of these have their separate and important use casses
we generally use TCP when we need security and reliability for eg: Message sharing, confidential communication, emails
file transfers, banking transaction where serurity and reliability is mandatory

whereas UDP is used when dropping of few packets wont affect the user, such as gaming, vidoe streaming, online meetings etc
where security is not that mandatory




 */