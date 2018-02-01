         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 3
         sub    r4, r4, r4
         addi   r4, r4, 3
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 2
         sub    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 3
         sub    r5, r5, r5
         muli   r5, r4, 2
         sub    r4, r4, r5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 5
         sub    r5, r5, r5
         addi   r5, r5, 2
         div    r2, r4, r5
         sub    r4, r4, r4
         add    r4, r3, r2
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r5, r5, r5
         addi   r5, r5, 0
         sub    r6, r6, r6
         not    r6, r5
         bz     r6, 176
         addi   r5, r0, 1
         j      180
         addi   r5, r0, 0
         and    r2, r3, r5
         bz     r2, 196
         addi   r2, r0, 1
         j      200
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r5, r5, r5
         addi   r5, r5, 0
         or     r3, r2, r5
         bz     r3, 228
         addi   r3, r0, 1
         j      232
         addi   r3, r0, 0
         sub    r2, r2, r2
         cgt    r2, r4, r3
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 3
         sub    r4, r4, r4
         addi   r4, r4, 3
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 2
         sub    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 3
         sub    r5, r5, r5
         muli   r5, r4, 2
         sub    r4, r4, r5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 5
         sub    r5, r5, r5
         addi   r5, r5, 2
         div    r2, r4, r5
         sub    r4, r4, r4
         add    r4, r3, r2
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r5, r5, r5
         addi   r5, r5, 0
         sub    r6, r6, r6
         not    r6, r5
         bz     r6, 412
         addi   r5, r0, 1
         j      416
         addi   r5, r0, 0
         and    r2, r3, r5
         bz     r2, 432
         addi   r2, r0, 1
         j      436
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r5, r5, r5
         addi   r5, r5, 0
         or     r3, r2, r5
         bz     r3, 464
         addi   r3, r0, 1
         j      468
         addi   r3, r0, 0
         sub    r2, r2, r2
         cge    r2, r4, r3
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 3
         sub    r4, r4, r4
         addi   r4, r4, 3
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 2
         sub    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 3
         sub    r5, r5, r5
         muli   r5, r4, 2
         sub    r4, r4, r5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 5
         sub    r5, r5, r5
         addi   r5, r5, 2
         div    r2, r4, r5
         sub    r4, r4, r4
         add    r4, r3, r2
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r5, r5, r5
         addi   r5, r5, 0
         sub    r6, r6, r6
         not    r6, r5
         bz     r6, 648
         addi   r5, r0, 1
         j      652
         addi   r5, r0, 0
         and    r2, r3, r5
         bz     r2, 668
         addi   r2, r0, 1
         j      672
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r5, r5, r5
         addi   r5, r5, 0
         or     r3, r2, r5
         bz     r3, 700
         addi   r3, r0, 1
         j      704
         addi   r3, r0, 0
         sub    r2, r2, r2
         clt    r2, r4, r3
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 3
         sub    r4, r4, r4
         addi   r4, r4, 3
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 2
         sub    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 3
         sub    r5, r5, r5
         muli   r5, r4, 2
         sub    r4, r4, r5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 5
         sub    r5, r5, r5
         addi   r5, r5, 2
         div    r2, r4, r5
         sub    r4, r4, r4
         add    r4, r3, r2
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r5, r5, r5
         addi   r5, r5, 0
         sub    r6, r6, r6
         not    r6, r5
         bz     r6, 884
         addi   r5, r0, 1
         j      888
         addi   r5, r0, 0
         and    r2, r3, r5
         bz     r2, 904
         addi   r2, r0, 1
         j      908
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r5, r5, r5
         addi   r5, r5, 0
         or     r3, r2, r5
         bz     r3, 936
         addi   r3, r0, 1
         j      940
         addi   r3, r0, 0
         sub    r2, r2, r2
         cle    r2, r4, r3
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 3
         sub    r4, r4, r4
         addi   r4, r4, 3
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 2
         sub    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 3
         sub    r5, r5, r5
         muli   r5, r4, 2
         sub    r4, r4, r5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 5
         sub    r5, r5, r5
         addi   r5, r5, 2
         div    r2, r4, r5
         sub    r4, r4, r4
         add    r4, r3, r2
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r5, r5, r5
         addi   r5, r5, 0
         sub    r6, r6, r6
         not    r6, r5
         bz     r6, 1120
         addi   r5, r0, 1
         j      1124
         addi   r5, r0, 0
         and    r2, r3, r5
         bz     r2, 1140
         addi   r2, r0, 1
         j      1144
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r5, r5, r5
         addi   r5, r5, 0
         or     r3, r2, r5
         bz     r3, 1172
         addi   r3, r0, 1
         j      1176
         addi   r3, r0, 0
         sub    r2, r2, r2
         ceq    r2, r4, r3
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 3
         sub    r4, r4, r4
         addi   r4, r4, 3
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 2
         sub    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 3
         sub    r5, r5, r5
         muli   r5, r4, 2
         sub    r4, r4, r5
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 5
         sub    r5, r5, r5
         addi   r5, r5, 2
         div    r2, r4, r5
         sub    r4, r4, r4
         add    r4, r3, r2
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r5, r5, r5
         addi   r5, r5, 0
         sub    r6, r6, r6
         not    r6, r5
         bz     r6, 1356
         addi   r5, r0, 1
         j      1360
         addi   r5, r0, 0
         and    r2, r3, r5
         bz     r2, 1376
         addi   r2, r0, 1
         j      1380
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r5, r5, r5
         addi   r5, r5, 0
         or     r3, r2, r5
         bz     r3, 1408
         addi   r3, r0, 1
         j      1412
         addi   r3, r0, 0
         sub    r2, r2, r2
         cgt    r2, r4, r3
         bz     r2, 1428
         j      1432
         clt    r2, r4, r3
         add    r1, r1, r14
         sw     0(r1), r2
         hlt   
