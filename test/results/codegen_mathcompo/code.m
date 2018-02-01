 A_meth  sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r2, r2, r2

         % debug4
         sub    r3, r3, r3
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, 0 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         muli   r4, r4, 4
         add    r3, r3, r4

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r4, r4, r4
         addi   r3, r3, 4
         muli   r4, r3, 2 % debug1
         sub    r3, r3, r4 % debug2
         add    r3, r3, r12 % Calculate address to variable and set it up into a register (With method register as offset
         lw     r2, 0(r3) % Load variable
         sw     0(r1), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -32 %  Increasing stack pointer by 32
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -12 %  Increasing stack pointer by 12

         % debug4

         % array register evaluation begin 2
         sub    r1, r1, r1
         sub    r3, r3, r3
         addi   r3, r3, 0
         muli   r3, r3, 4
         add    r1, r1, r3

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r1, r1, 44
         muli   r3, r1, 2 % debug1
         sub    r1, r1, r3 % debug2
         sub    r3, r3, r3
         addi   r3, r3, 40
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4

         % array register evaluation begin 2
         sub    r1, r1, r1
         sub    r3, r3, r3
         addi   r3, r3, 1
         muli   r3, r3, 4
         add    r1, r1, r3

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r1, r1, 44
         muli   r3, r1, 2 % debug1
         sub    r1, r1, r3 % debug2
         sub    r3, r3, r3
         addi   r3, r3, 18
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -36 % debug3
         sub    r3, r3, r3
         addi   r3, r3, 5
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4
         sub    r1, r1, r1
         sub    r3, r3, r3
         addi   r3, r3, 1
         muli   r3, r3, 16
         add    r1, r1, r3
         sub    r3, r3, r3
         addi   r3, r3, 1
         muli   r3, r3, 4
         add    r1, r1, r3

         % identifier == null debug
         sub    r3, r3, r3
         addi   r1, r1, 4
         muli   r3, r1, 2 % debug1
         sub    r1, r1, r3 % debug2
         sub    r3, r3, r3
         addi   r3, r3, 99
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r3, r3, r3
         sub    r4, r4, r4

         % debug4
         sub    r5, r5, r5
         sub    r6, r6, r6
         sub    r7, r7, r7
         addi   r7, r7, 1
         sub    r8, r8, r8
         addi   r8, r8, 0
         or     r6, r7, r8
         bz     r6, 352
         addi   r6, r0, 1
         j      356
         addi   r6, r0, 0
         muli   r6, r6, 16
         add    r5, r5, r6
         sub    r6, r6, r6
         sub    r7, r7, r7
         addi   r7, r7, 4
         sub    r8, r8, r8
         addi   r8, r8, 3
         sub    r6, r7, r8
         muli   r6, r6, 4
         add    r5, r5, r6

         % identifier == null debug
         sub    r6, r6, r6
         addi   r5, r5, 4
         muli   r6, r5, 2 % debug1
         sub    r5, r5, r6 % debug2
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         sub    r5, r5, r5

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 0 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun1
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r2, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 4 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun2
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r5, 0(r13) % Loading up the function returned value into a register
         mul    r3, r4, r5
         sub    r4, r4, r4
         sub    r5, r5, r5

         % debug4

         % identifier == null debug
         sub    r6, r6, r6
         addi   r6, r0, -36 % debug3
         add    r6, r6, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r6) % Load variable
         mul    r4, r3, r5
         sub    r3, r3, r3
         sub    r5, r5, r5

         % Function evaluation begin.

         % debug4

         % identifier == null debug
         sub    r6, r6, r6
         addi   r6, r0, -40 % debug3

         % Function evaluation ends.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         add    r12, r14, r6 % The function that is about to be called is a method. We need to put a pointer to the instance in a regsiter

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r4, r4, r4
         addi   r4, r4, 1
         and    r2, r3, r4
         bz     r2, 936
         addi   r2, r0, 1
         j      940
         addi   r2, r0, 0
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 1
         or     r3, r2, r4
         bz     r3, 968
         addi   r3, r0, 1
         j      972
         addi   r3, r0, 0
         sub    r2, r2, r2
         sub    r4, r4, r4

         % debug4
         sub    r5, r5, r5
         sub    r6, r6, r6
         addi   r6, r6, 1
         muli   r6, r6, 16
         add    r5, r5, r6
         sub    r6, r6, r6

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 1
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 4 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun2
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r6, 0(r13) % Loading up the function returned value into a register
         muli   r6, r6, 4
         add    r5, r5, r6

         % identifier == null debug
         sub    r6, r6, r6
         addi   r5, r5, 4
         muli   r6, r5, 2 % debug1
         sub    r5, r5, r6 % debug2
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         or     r2, r3, r4
         bz     r2, 1236
         addi   r2, r0, 1
         j      1240
         addi   r2, r0, 0
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 4 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, A_meth
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r5, 0(r13) % Loading up the function returned value into a register
         add    r3, r4, r5
         sub    r4, r4, r4
         sub    r5, r5, r5

         % debug4

         % array register evaluation begin 2
         sub    r7, r7, r7
         sub    r8, r8, r8
         sub    r9, r9, r9

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 0 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun1
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r9, 0(r13) % Loading up the function returned value into a register
         sub    r10, r10, r10
         addi   r10, r10, 2
         sub    r8, r9, r10
         muli   r8, r8, 4
         add    r7, r7, r8

         % array register evaluation end 2

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r8, r8, r8
         addi   r7, r7, 44
         muli   r8, r7, 2 % debug1
         sub    r7, r7, r8 % debug2
         add    r7, r7, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r7) % Load variable
         add    r4, r3, r5
         sub    r3, r3, r3
         sub    r5, r5, r5

         % debug4

         % identifier == null debug
         sub    r7, r7, r7
         addi   r7, r0, -36 % debug3
         add    r7, r7, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r7) % Load variable
         add    r3, r4, r5
         sub    r4, r4, r4
         sub    r5, r5, r5
         addi   r5, r5, 100
         div    r4, r3, r5
         add    r1, r1, r14
         sw     0(r1), r4
         sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r3) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         hlt   
 fun1    sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r3, r3, r3
         addi   r3, r3, 3
         sw     0(r1), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
 fun2    sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r4, r4, r4
         sub    r5, r5, r5

         % debug4

         % identifier == null debug
         sub    r7, r7, r7
         addi   r7, r0, 0 % debug3
         add    r7, r7, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r7) % Load variable
         sub    r7, r7, r7
         addi   r7, r7, 3
         mul    r4, r5, r7
         sw     0(r1), r4 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
