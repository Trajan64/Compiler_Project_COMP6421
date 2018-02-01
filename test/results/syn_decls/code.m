 A_meth  sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r1), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         hlt   
