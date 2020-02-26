# myTest
fightorder package 下面是自己写的一个多线程的抢单程序，逻辑是客户发出订单，放入DelayQueue，然后通过推送的方式发到队员的手机上，
队员进行抢单，1分钟没有人抢单，则从DelayQueue内取出，进入自动派单逻辑，涉及到的设计模式有模板方法，单例等
