         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 2
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -12 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor0  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -12 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren0
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -16 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor1  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -16 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren1

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -12 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -16 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r3, r2, r4
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -16 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -16 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor1
 foren1  subi   r13, r13, -4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -16 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor2  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -16 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -12 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -16 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r3, r2, r4
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -16 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -16 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor2
 foren2  subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -12 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -12 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor0
 foren0  subi   r13, r13, -4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -12 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor3  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -12 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren3
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -16 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor4  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -16 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -20 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor5  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -20 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren5

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -12 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -16 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -20 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -20 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -20 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor5
 foren5  subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -16 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -16 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor4
 foren4  subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -12 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -12 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor3
 foren3  subi   r13, r13, -4
         sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r2, r2, r2
         addi   r2, r0, -4 % debug3
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
         sub    r1, r1, r1
         sub    r2, r2, r2
         addi   r2, r2, 1
         sub    r3, r3, r3
         addi   r3, r3, 0
         and    r1, r2, r3
         bz     r1, 1316
         addi   r1, r0, 1
         j      1320
         addi   r1, r0, 0
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 0
         or     r2, r3, r1
         bz     r2, 1348
         addi   r2, r0, 1
         j      1352
         addi   r2, r0, 0
         sub    r1, r1, r1
         sub    r3, r3, r3
         addi   r3, r3, 1
         or     r1, r2, r3
         bz     r1, 1380
         addi   r1, r0, 1
         j      1384
         addi   r1, r0, 0
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 0
         sub    r4, r4, r4
         addi   r4, r4, 0
         and    r2, r3, r4
         bz     r2, 1420
         addi   r2, r0, 1
         j      1424
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 0
         or     r3, r4, r2
         bz     r3, 1452
         addi   r3, r0, 1
         j      1456
         addi   r3, r0, 0
         sub    r2, r2, r2
         cgt    r2, r1, r3
         bz     r2, else0

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -8 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 2
         add    r1, r1, r14
         sw     0(r1), r2
         sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -8 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         addi   r3, r3, 2
         ceq    r1, r2, r3
         bz     r1, else1

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 10
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      endif1

         % debug4

         % identifier == null debug
 else1   sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         sub    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
 endif1  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -8 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         addi   r3, r3, 2
         cgt    r1, r2, r3
         bz     r1, else2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         sub    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      endif2

         % debug4

         % identifier == null debug
 else2   sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 10
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
 endif2  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -8 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         addi   r3, r3, 2
         cge    r1, r2, r3
         bz     r1, else3

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 10
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      endif3

         % debug4

         % identifier == null debug
 else3   sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         sub    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
 endif3  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -8 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         addi   r3, r3, 2
         clt    r1, r2, r3
         bz     r1, else4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         sub    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      endif4

         % debug4

         % identifier == null debug
 else4   sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 10
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
 endif4  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -8 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         addi   r3, r3, 2
         cle    r1, r2, r3
         bz     r1, else5

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 10
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      endif5

         % debug4

         % identifier == null debug
 else5   sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         sub    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
 endif5  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -8 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         addi   r3, r3, 3
         cgt    r1, r2, r3
         bz     r1, 2276
         j      2280
         clt    r1, r2, r3
         bz     r1, else6

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 10
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      endif6

         % debug4

         % identifier == null debug
 else6   sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         sub    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
 endif6  nop   
         j      endif0
 else0   nop   
 endif0  sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r2, r2, r2
         addi   r2, r0, -4 % debug3
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         hlt   
