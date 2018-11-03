
      <div class="wedding-barrage" ref="barrage" :style="{opacity:barrageData?1:0}">
        <p class="code barrage-0" ref="barrageFirst" :style="{transform:'translate('+initialOffset+'px)',top:'10px'}">
          <span class="mine">{{ wish }}</span>
          <span v-for="item in filterBarrage(barrageData,0)">{{ item }}</span>
        </p>
        <p class="code barrage-1" ref="barrageSecond" :style="{transform:'translate('+initialOffset+'px)',top:'40px'}">
          <span v-for="item in filterBarrage(barrageData,1)">{{ item }}</span>
        </p>
        <p class="code barrage-2" ref="barrageThird" :style="{transform:'translate('+initialOffset+'px)',top:'70px'}">
          <span v-for="item in filterBarrage(barrageData,2)">{{ item }}</span>
        </p>
        <p class="code barrage-3" ref="barrageFourth" :style="{transform:'translate('+initialOffset+'px)',top:'100px'}">
          <span v-for="item in filterBarrage(barrageData,3)">{{ item }}</span>
        </p>
        <div class="barrage-space"></div>
      </div>