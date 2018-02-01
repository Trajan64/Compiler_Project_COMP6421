         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -192 %  Increasing stack pointer by 192
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -224 %  Increasing stack pointer by 224
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -436 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 2
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -440 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -192 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 2
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -196 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -200 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 2
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -204 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 3
         add    r1, r1, r14
         sw     0(r1), r2
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor0  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -436 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren0
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor1  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -440 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren1

         % debug4
         sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 112
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 28
         add    r1, r1, r2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r2, r2, r2
         addi   r1, r1, 224
         muli   r2, r1, 2 % debug1
         sub    r1, r1, r2 % debug2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -432 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4
         sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 112
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 28
         add    r1, r1, r2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r2, r2, r2
         addi   r1, r1, 228
         muli   r2, r1, 2 % debug1
         sub    r1, r1, r2 % debug2
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -432 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 10
         mul    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4
         sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 112
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 28
         add    r1, r1, r2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r2, r2, r2
         addi   r1, r1, 232
         muli   r2, r1, 2 % debug1
         sub    r1, r1, r2 % debug2
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -432 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 100
         mul    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -432 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -432 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor1
 foren1  subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
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
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor2  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -436 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren2
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor3  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -440 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren3
         sub    r1, r1, r1

         % debug4
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 112
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 28
         add    r2, r2, r3

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r2, r2, 224
         muli   r3, r2, 2 % debug1
         sub    r2, r2, r3 % debug2
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % debug4
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 112
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 28
         add    r2, r2, r3

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r2, r2, 228
         muli   r3, r2, 2 % debug1
         sub    r2, r2, r3 % debug2
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % debug4
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 112
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 28
         add    r2, r2, r3

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r2, r2, 232
         muli   r3, r2, 2 % debug1
         sub    r2, r2, r3 % debug2
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
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor3
 foren3  subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor2
 foren2  subi   r13, r13, -4
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -432 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor4  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -436 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor5  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -440 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren5

         % debug4
         sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 112
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 28
         add    r1, r1, r2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2
         sub    r2, r2, r2
         addi   r2, r2, 0
         muli   r2, r2, 8
         add    r1, r1, r2

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r2, r2, r2
         addi   r1, r1, 208
         muli   r2, r1, 2 % debug1
         sub    r1, r1, r2 % debug2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -432 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4
         sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 112
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 28
         add    r1, r1, r2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2
         sub    r2, r2, r2
         addi   r2, r2, 1
         muli   r2, r2, 8
         add    r1, r1, r2

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r2, r2, r2
         addi   r1, r1, 208
         muli   r2, r1, 2 % debug1
         sub    r1, r1, r2 % debug2
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -432 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -432 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -432 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 2
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
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
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor4
 foren4  subi   r13, r13, -4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor6  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -436 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren6
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor7  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -440 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren7
         sub    r1, r1, r1

         % debug4
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 112
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 28
         add    r2, r2, r3

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2
         sub    r3, r3, r3
         addi   r3, r3, 0
         muli   r3, r3, 8
         add    r2, r2, r3

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r2, r2, 208
         muli   r3, r2, 2 % debug1
         sub    r2, r2, r3 % debug2
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % debug4
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 112
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 28
         add    r2, r2, r3

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2
         sub    r3, r3, r3
         addi   r3, r3, 1
         muli   r3, r3, 8
         add    r2, r2, r3

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r2, r2, 208
         muli   r3, r2, 2 % debug1
         sub    r2, r2, r3 % debug2
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
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor7
 foren7  subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor6
 foren6  subi   r13, r13, -4
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -432 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor8  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -192 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren8
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor9  sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -196 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren9
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -452 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor10 sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -452 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -200 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren10
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -456 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor11 sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -456 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -204 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren11

         % debug4
         sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 96
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 24
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -452 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 12
         add    r1, r1, r2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -456 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         muli   r2, r2, 4
         add    r1, r1, r2

         % identifier == null debug
         sub    r2, r2, r2
         addi   r1, r1, 0
         muli   r2, r1, 2 % debug1
         sub    r1, r1, r2 % debug2
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -432 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -432 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -432 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -456 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -456 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor11
 foren11 subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -452 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -452 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor10
 foren10 subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor9
 foren9  subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor8
 foren8  subi   r13, r13, -4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor12 sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -444 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -192 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren12
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor13 sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -448 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -196 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren13
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -452 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor14 sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -452 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -200 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren14
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -456 % debug3
         sub    r2, r2, r2
         addi   r2, r2, 0
         add    r1, r1, r14
         sw     0(r1), r2
 gofor15 sub    r1, r1, r1
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -456 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -204 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         clt    r1, r2, r3
         bz     r1, foren15
         sub    r1, r1, r1

         % debug4
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 96
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 24
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -452 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 12
         add    r2, r2, r3
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -456 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         muli   r3, r3, 4
         add    r2, r2, r3

         % identifier == null debug
         sub    r3, r3, r3
         addi   r2, r2, 0
         muli   r3, r2, 2 % debug1
         sub    r2, r2, r3 % debug2
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
         addi   r1, r0, -456 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -456 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor15
 foren15 subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -452 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -452 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor14
 foren14 subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -448 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -448 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor13
 foren13 subi   r13, r13, -4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -444 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -444 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4
         addi   r4, r4, 1
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         j      gofor12
 foren12 subi   r13, r13, -4
         hlt   
