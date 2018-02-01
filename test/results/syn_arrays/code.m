         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -32 %  Increasing stack pointer by 32
         addi   r13, r13, -36 %  Increasing stack pointer by 36

         % debug4
         sub    r1, r1, r1
         sub    r2, r2, r2
         addi   r2, r2, 0
         muli   r2, r2, 16
         add    r1, r1, r2
         sub    r2, r2, r2
         addi   r2, r2, 0
         muli   r2, r2, 4
         add    r1, r1, r2

         % identifier == null debug
         sub    r2, r2, r2
         addi   r1, r1, 0
         muli   r2, r1, 2 % debug1
         sub    r1, r1, r2 % debug2
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4
         sub    r4, r4, r4
         sub    r5, r5, r5

         % debug4
         sub    r6, r6, r6
         sub    r7, r7, r7

         % debug4
         sub    r8, r8, r8
         sub    r9, r9, r9
         addi   r9, r9, 0
         muli   r9, r9, 16
         add    r8, r8, r9
         sub    r9, r9, r9
         addi   r9, r9, 0
         muli   r9, r9, 4
         add    r8, r8, r9

         % identifier == null debug
         sub    r9, r9, r9
         addi   r8, r8, 0
         muli   r9, r8, 2 % debug1
         sub    r8, r8, r9 % debug2
         add    r8, r8, r14 % Calculate address to variable and set it up into a register
         lw     r7, 0(r8) % Load variable
         muli   r7, r7, 16
         add    r6, r6, r7
         sub    r7, r7, r7
         addi   r7, r7, 0
         muli   r7, r7, 4
         add    r6, r6, r7

         % identifier == null debug
         sub    r7, r7, r7
         addi   r6, r6, 0
         muli   r7, r6, 2 % debug1
         sub    r6, r6, r7 % debug2
         add    r6, r6, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r6) % Load variable
         muli   r5, r5, 16
         add    r4, r4, r5
         sub    r5, r5, r5
         addi   r5, r5, 0
         muli   r5, r5, 4
         add    r4, r4, r5

         % identifier == null debug
         sub    r5, r5, r5
         addi   r4, r4, 0
         muli   r5, r4, 2 % debug1
         sub    r4, r4, r5 % debug2
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4

         % debug4
         sub    r5, r5, r5
         sub    r6, r6, r6

         % debug4
         sub    r7, r7, r7
         sub    r8, r8, r8
         addi   r8, r8, 0
         muli   r8, r8, 4
         add    r7, r7, r8

         % identifier == null debug
         sub    r8, r8, r8
         addi   r7, r7, 32
         muli   r8, r7, 2 % debug1
         sub    r7, r7, r8 % debug2
         add    r7, r7, r14 % Calculate address to variable and set it up into a register
         lw     r6, 0(r7) % Load variable
         muli   r6, r6, 4
         add    r5, r5, r6

         % identifier == null debug
         sub    r6, r6, r6
         addi   r5, r5, 32
         muli   r6, r5, 2 % debug1
         sub    r5, r5, r6 % debug2
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2
         hlt   
