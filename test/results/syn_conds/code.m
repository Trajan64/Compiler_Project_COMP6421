         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         sub    r1, r1, r1
         sub    r2, r2, r2
         addi   r2, r2, 2
         sub    r3, r3, r3
         addi   r3, r3, 1
         cgt    r1, r2, r3
         bz     r1, else0
         sub    r1, r1, r1
         addi   r1, r1, 1
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         j      endif0
 else0   sub    r1, r1, r1
         addi   r1, r1, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         sub    r2, r2, r2
         addi   r2, r2, 2
         sub    r3, r3, r3
         addi   r3, r3, 1
         ceq    r1, r2, r3
         bz     r1, else1
         sub    r1, r1, r1
         addi   r1, r1, 1
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         j      endif1
 else1   sub    r1, r1, r1
         addi   r1, r1, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
 endif1  nop   
 endif0  addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor0  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         addi   r3, r3, 99
         cle    r1, r2, r3
         bz     r1, foren0
         sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r2, r2, r2
         addi   r2, r0, 0 % debug3
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
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor0
 foren0  subi   r13, r13, -4
         hlt   
