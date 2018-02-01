         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -220 %  Increasing stack pointer by 220
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -220 % debug3
         sub    r2, r2, r2

         % debug4

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -220 % debug3
         sub    r2, r2, r2

         % debug4

         % array register evaluation begin 2
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 0
         muli   r4, r4, 24
         add    r3, r3, r4

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r4, r4, r4
         addi   r3, r3, 4
         muli   r4, r3, 2 % debug1
         sub    r3, r3, r4 % debug2
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -220 % debug3
         sub    r2, r2, r2

         % debug4

         % array register evaluation begin 2
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 0
         muli   r4, r4, 24
         add    r3, r3, r4

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r4, r4, r4
         addi   r3, r3, 8
         muli   r4, r3, 2 % debug1
         sub    r3, r3, r4 % debug2
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -220 % debug3
         sub    r2, r2, r2

         % debug4

         % array register evaluation begin 2
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 0
         muli   r4, r4, 24
         add    r3, r3, r4

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % array register evaluation begin 2
         sub    r4, r4, r4
         addi   r4, r4, 0
         muli   r4, r4, 8
         add    r3, r3, r4
         sub    r4, r4, r4
         addi   r4, r4, 0
         muli   r4, r4, 4
         add    r3, r3, r4

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r4, r4, r4
         addi   r3, r3, 12
         muli   r4, r3, 2 % debug1
         sub    r3, r3, r4 % debug2
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r14
         sw     0(r1), r2
         hlt   
